import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IThing } from 'app/shared/model/ThingMJ/thing.model';

type EntityResponseType = HttpResponse<IThing>;
type EntityArrayResponseType = HttpResponse<IThing[]>;

@Injectable({ providedIn: 'root' })
export class ThingService {
    private resourceUrl = SERVER_API_URL + 'ThingMJ/api/things';

    constructor(private http: HttpClient) {}

    create(thing: IThing): Observable<EntityResponseType> {
        return this.http.post<IThing>(this.resourceUrl, thing, { observe: 'response' });
    }

    update(thing: IThing): Observable<EntityResponseType> {
        return this.http.put<IThing>(this.resourceUrl, thing, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IThing>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IThing[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
