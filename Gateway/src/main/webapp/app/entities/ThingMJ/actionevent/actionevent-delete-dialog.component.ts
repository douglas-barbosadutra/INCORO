import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActionevent } from 'app/shared/model/ThingMJ/actionevent.model';
import { ActioneventService } from './actionevent.service';

@Component({
    selector: 'jhi-actionevent-delete-dialog',
    templateUrl: './actionevent-delete-dialog.component.html'
})
export class ActioneventDeleteDialogComponent {
    actionevent: IActionevent;

    constructor(
        private actioneventService: ActioneventService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.actioneventService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'actioneventListModification',
                content: 'Deleted an actionevent'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-actionevent-delete-popup',
    template: ''
})
export class ActioneventDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ actionevent }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ActioneventDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.actionevent = actionevent;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
