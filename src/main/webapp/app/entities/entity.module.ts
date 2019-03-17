import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'customer',
                loadChildren: './customer/customer.module#DemoJhipsterCustomerModule'
            },
            {
                path: 'order',
                loadChildren: './order/order.module#DemoJhipsterOrderModule'
            },
            {
                path: 'order',
                loadChildren: './order/order.module#DemoJhipsterOrderModule'
            },
            {
                path: 'order',
                loadChildren: './order/order.module#DemoJhipsterOrderModule'
            },
            {
                path: 'order',
                loadChildren: './order/order.module#DemoJhipsterOrderModule'
            },
            {
                path: 'order-line',
                loadChildren: './order-line/order-line.module#DemoJhipsterOrderLineModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DemoJhipsterEntityModule {}
