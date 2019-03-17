import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IOrder {
    id?: number;
    date?: Moment;
    billNumber?: string;
    amount?: number;
    status?: string;
    customer?: ICustomer;
}

export class Order implements IOrder {
    constructor(
        public id?: number,
        public date?: Moment,
        public billNumber?: string,
        public amount?: number,
        public status?: string,
        public customer?: ICustomer
    ) {}
}
