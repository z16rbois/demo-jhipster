import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrderLine } from 'app/shared/model/order-line.model';

type EntityResponseType = HttpResponse<IOrderLine>;
type EntityArrayResponseType = HttpResponse<IOrderLine[]>;

@Injectable({ providedIn: 'root' })
export class OrderLineService {
    public resourceUrl = SERVER_API_URL + 'api/order-lines';

    constructor(protected http: HttpClient) {}

    create(orderLine: IOrderLine): Observable<EntityResponseType> {
        return this.http.post<IOrderLine>(this.resourceUrl, orderLine, { observe: 'response' });
    }

    update(orderLine: IOrderLine): Observable<EntityResponseType> {
        return this.http.put<IOrderLine>(this.resourceUrl, orderLine, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOrderLine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOrderLine[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
