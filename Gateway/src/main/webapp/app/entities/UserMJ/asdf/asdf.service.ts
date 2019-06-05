import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAsdf } from 'app/shared/model/UserMJ/asdf.model';

type EntityResponseType = HttpResponse<IAsdf>;
type EntityArrayResponseType = HttpResponse<IAsdf[]>;

@Injectable({ providedIn: 'root' })
export class AsdfService {
    private resourceUrl = SERVER_API_URL + 'UserMJ/api/asdfs';

    constructor(private http: HttpClient) {}

    create(asdf: IAsdf): Observable<EntityResponseType> {
        return this.http.post<IAsdf>(this.resourceUrl, asdf, { observe: 'response' });
    }

    update(asdf: IAsdf): Observable<EntityResponseType> {
        return this.http.put<IAsdf>(this.resourceUrl, asdf, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IAsdf>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAsdf[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
