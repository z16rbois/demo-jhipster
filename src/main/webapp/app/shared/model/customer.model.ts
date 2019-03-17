import { IOrder } from 'app/shared/model/order.model';

export interface ICustomer {
    id?: number;
    firstName?: string;
    lastName?: string;
    orders?: IOrder[];
}

export class Customer implements ICustomer {
    constructor(public id?: number, public firstName?: string, public lastName?: string, public orders?: IOrder[]) {}
}
