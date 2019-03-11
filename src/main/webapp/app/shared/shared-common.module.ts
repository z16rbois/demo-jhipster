import { NgModule } from '@angular/core';

import { DemoJhipsterSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [DemoJhipsterSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [DemoJhipsterSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class DemoJhipsterSharedCommonModule {}
