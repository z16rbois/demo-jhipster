import { IOrder } from 'app/shared/model/order.model';

export interface IOrderLine {
    id?: number;
    quantity?: number;
    label?: string;
    unitPrice?: number;
    order?: IOrder;
}

export class OrderLine implements IOrderLine {
    constructor(public id?: number, public quantity?: number, public label?: string, public unitPrice?: number, public order?: IOrder) {}
}
