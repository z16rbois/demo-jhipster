import { element, by, ElementFinder } from 'protractor';

export class OrderLineComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-order-line div table .btn-danger'));
    title = element.all(by.css('jhi-order-line div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getText();
    }
}

export class OrderLineUpdatePage {
    pageTitle = element(by.id('jhi-order-line-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    quantityInput = element(by.id('field_quantity'));
    labelInput = element(by.id('field_label'));
    unitPriceInput = element(by.id('field_unitPrice'));
    orderSelect = element(by.id('field_order'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setQuantityInput(quantity) {
        await this.quantityInput.sendKeys(quantity);
    }

    async getQuantityInput() {
        return this.quantityInput.getAttribute('value');
    }

    async setLabelInput(label) {
        await this.labelInput.sendKeys(label);
    }

    async getLabelInput() {
        return this.labelInput.getAttribute('value');
    }

    async setUnitPriceInput(unitPrice) {
        await this.unitPriceInput.sendKeys(unitPrice);
    }

    async getUnitPriceInput() {
        return this.unitPriceInput.getAttribute('value');
    }

    async orderSelectLastOption() {
        await this.orderSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async orderSelectOption(option) {
        await this.orderSelect.sendKeys(option);
    }

    getOrderSelect(): ElementFinder {
        return this.orderSelect;
    }

    async getOrderSelectedOption() {
        return this.orderSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class OrderLineDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-orderLine-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-orderLine'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
