import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Operation } from './operation.model';
import { OperationPopupService } from './operation-popup.service';
import { OperationService } from './operation.service';

@Component({
    selector: 'jhi-operation-dialog',
    templateUrl: './operation-dialog.component.html'
})
export class OperationDialogComponent implements OnInit {

    operation: Operation;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private operationService: OperationService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.operation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.operationService.update(this.operation), false);
        } else {
            this.subscribeToSaveResponse(
                this.operationService.create(this.operation), true);
        }
    }

    private subscribeToSaveResponse(result: Observable<Operation>, isCreated: boolean) {
        result.subscribe((res: Operation) =>
            this.onSaveSuccess(res, isCreated), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Operation, isCreated: boolean) {
        this.alertService.success(
            isCreated ? `A new Operation is created with identifier ${result.id}`
            : `A Operation is updated with identifier ${result.id}`,
            null, null);

        this.eventManager.broadcast({ name: 'operationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-operation-popup',
    template: ''
})
export class OperationPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private operationPopupService: OperationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.operationPopupService
                    .open(OperationDialogComponent, params['id']);
            } else {
                this.modalRef = this.operationPopupService
                    .open(OperationDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
