/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderLineComponentsPage, OrderLineDeleteDialog, OrderLineUpdatePage } from './order-line.page-object';

const expect = chai.expect;

describe('OrderLine e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let orderLineUpdatePage: OrderLineUpdatePage;
    let orderLineComponentsPage: OrderLineComponentsPage;
    let orderLineDeleteDialog: OrderLineDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load OrderLines', async () => {
        await navBarPage.goToEntity('order-line');
        orderLineComponentsPage = new OrderLineComponentsPage();
        await browser.wait(ec.visibilityOf(orderLineComponentsPage.title), 5000);
        expect(await orderLineComponentsPage.getTitle()).to.eq('Order Lines');
    });

    it('should load create OrderLine page', async () => {
        await orderLineComponentsPage.clickOnCreateButton();
        orderLineUpdatePage = new OrderLineUpdatePage();
        expect(await orderLineUpdatePage.getPageTitle()).to.eq('Create or edit a Order Line');
        await orderLineUpdatePage.cancel();
    });

    it('should create and save OrderLines', async () => {
        const nbButtonsBeforeCreate = await orderLineComponentsPage.countDeleteButtons();

        await orderLineComponentsPage.clickOnCreateButton();
        await promise.all([
            orderLineUpdatePage.setQuantityInput('5'),
            orderLineUpdatePage.setLabelInput('label'),
            orderLineUpdatePage.setUnitPriceInput('5'),
            orderLineUpdatePage.orderSelectLastOption()
        ]);
        expect(await orderLineUpdatePage.getQuantityInput()).to.eq('5');
        expect(await orderLineUpdatePage.getLabelInput()).to.eq('label');
        expect(await orderLineUpdatePage.getUnitPriceInput()).to.eq('5');
        await orderLineUpdatePage.save();
        expect(await orderLineUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await orderLineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last OrderLine', async () => {
        const nbButtonsBeforeDelete = await orderLineComponentsPage.countDeleteButtons();
        await orderLineComponentsPage.clickOnLastDeleteButton();

        orderLineDeleteDialog = new OrderLineDeleteDialog();
        expect(await orderLineDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Order Line?');
        await orderLineDeleteDialog.clickOnConfirmButton();

        expect(await orderLineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
