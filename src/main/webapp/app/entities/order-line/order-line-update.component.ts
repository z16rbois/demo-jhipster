import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderLine } from 'app/shared/model/order-line.model';
import { OrderLineService } from './order-line.service';
import { IOrder } from 'app/shared/model/order.model';
import { OrderService } from 'app/entities/order';

@Component({
    selector: 'jhi-order-line-update',
    templateUrl: './order-line-update.component.html'
})
export class OrderLineUpdateComponent implements OnInit {
    orderLine: IOrderLine;
    isSaving: boolean;

    orders: IOrder[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected orderLineService: OrderLineService,
        protected orderService: OrderService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ orderLine }) => {
            this.orderLine = orderLine;
        });
        this.orderService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOrder[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOrder[]>) => response.body)
            )
            .subscribe((res: IOrder[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.orderLine.id !== undefined) {
            this.subscribeToSaveResponse(this.orderLineService.update(this.orderLine));
        } else {
            this.subscribeToSaveResponse(this.orderLineService.create(this.orderLine));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderLine>>) {
        result.subscribe((res: HttpResponse<IOrderLine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrderById(index: number, item: IOrder) {
        return item.id;
    }
}
