/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderComponentsPage, OrderDeleteDialog, OrderUpdatePage } from './order.page-object';

const expect = chai.expect;

describe('Order e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let orderUpdatePage: OrderUpdatePage;
    let orderComponentsPage: OrderComponentsPage;
    let orderDeleteDialog: OrderDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Orders', async () => {
        await navBarPage.goToEntity('order');
        orderComponentsPage = new OrderComponentsPage();
        await browser.wait(ec.visibilityOf(orderComponentsPage.title), 5000);
        expect(await orderComponentsPage.getTitle()).to.eq('Orders');
    });

    it('should load create Order page', async () => {
        await orderComponentsPage.clickOnCreateButton();
        orderUpdatePage = new OrderUpdatePage();
        expect(await orderUpdatePage.getPageTitle()).to.eq('Create or edit a Order');
        await orderUpdatePage.cancel();
    });

    it('should create and save Orders', async () => {
        const nbButtonsBeforeCreate = await orderComponentsPage.countDeleteButtons();

        await orderComponentsPage.clickOnCreateButton();
        await promise.all([
            orderUpdatePage.setDateInput('2000-12-31'),
            orderUpdatePage.setBillNumberInput('billNumber'),
            orderUpdatePage.setAmountInput('5'),
            orderUpdatePage.setStatusInput('status'),
            orderUpdatePage.setNameInput('name'),
            orderUpdatePage.customerSelectLastOption()
        ]);
        expect(await orderUpdatePage.getDateInput()).to.eq('2000-12-31');
        expect(await orderUpdatePage.getBillNumberInput()).to.eq('billNumber');
        expect(await orderUpdatePage.getAmountInput()).to.eq('5');
        expect(await orderUpdatePage.getStatusInput()).to.eq('status');
        expect(await orderUpdatePage.getNameInput()).to.eq('name');
        await orderUpdatePage.save();
        expect(await orderUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await orderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Order', async () => {
        const nbButtonsBeforeDelete = await orderComponentsPage.countDeleteButtons();
        await orderComponentsPage.clickOnLastDeleteButton();

        orderDeleteDialog = new OrderDeleteDialog();
        expect(await orderDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Order?');
        await orderDeleteDialog.clickOnConfirmButton();

        expect(await orderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
