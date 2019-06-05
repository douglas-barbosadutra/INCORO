/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AsdfService } from 'app/entities/UserMJ/asdf/asdf.service';
import { Asdf } from 'app/shared/model/UserMJ/asdf.model';
import { SERVER_API_URL } from 'app/app.constants';

describe('Service Tests', () => {
    describe('Asdf Service', () => {
        let injector: TestBed;
        let service: AsdfService;
        let httpMock: HttpTestingController;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AsdfService);
            httpMock = injector.get(HttpTestingController);
        });

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find('123').subscribe(() => {});

                const req = httpMock.expectOne({ method: 'GET' });

                const resourceUrl = SERVER_API_URL + 'usermj/api/asdfs';
                expect(req.request.url).toEqual(resourceUrl + '/' + '123');
            });

            it('should create a Asdf', () => {
                service.create(new Asdf(null)).subscribe(received => {
                    expect(received.body.id).toEqual(null);
                });

                const req = httpMock.expectOne({ method: 'POST' });
                req.flush({ id: null });
            });

            it('should update a Asdf', () => {
                service.update(new Asdf('123')).subscribe(received => {
                    expect(received.body.id).toEqual('123');
                });

                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush({ id: '123' });
            });

            it('should return a Asdf', () => {
                service.find('123').subscribe(received => {
                    expect(received.body.id).toEqual('123');
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush({ id: '123' });
            });

            it('should return a list of Asdf', () => {
                service.query(null).subscribe(received => {
                    expect(received.body[0].id).toEqual('123');
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush([new Asdf('123')]);
            });

            it('should delete a Asdf', () => {
                service.delete('123').subscribe(received => {
                    expect(received.url).toContain('/' + '123');
                });

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush(null);
            });

            it('should propagate not found response', () => {
                service.find('123').subscribe(null, (_error: any) => {
                    expect(_error.status).toEqual(404);
                });

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush('Invalid request parameters', {
                    status: 404,
                    statusText: 'Bad Request'
                });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
