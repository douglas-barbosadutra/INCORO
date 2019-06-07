import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IActionevent } from 'app/shared/model/ThingMJ/actionevent.model';

type EntityResponseType = HttpResponse<IActionevent>;
type EntityArrayResponseType = HttpResponse<IActionevent[]>;

@Injectable({ providedIn: 'root' })
export class ActioneventService {
    private resourceUrl = SERVER_API_URL + 'ThingMJ/api/actionevents';

    constructor(private http: HttpClient) {}

    create(actionevent: IActionevent): Observable<EntityResponseType> {
        return this.http.post<IActionevent>(this.resourceUrl, actionevent, { observe: 'response' });
    }

    update(actionevent: IActionevent): Observable<EntityResponseType> {
        return this.http.put<IActionevent>(this.resourceUrl, actionevent, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IActionevent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IActionevent[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
