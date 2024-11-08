import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class RestService {
    private readonly BASE_URL = environment.baseUrl;
    private readonly LOGIN_API = `${environment.baseUrl}/auth/login`;
    header = {
        headers: new HttpHeaders()
            .set('Authorization', `Bearer ${localStorage.getItem('accesstoken')}`)
    }

    constructor(private http: HttpClient) {

    }

    login(username, password) {
        return this.http.post<any>(this.LOGIN_API, { username, password })
            .pipe(map(response => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                if (response.token) {
                    localStorage.setItem('accesstoken', response['token']);
                    return true;
                }
                return false;
            }));
    }

    save(url, payload) {
        return this.http.post<any>(`${this.BASE_URL}${url}`, payload, this.header);
    }

    update(url, id, payload) {
        return this.http.put<any>(`${this.BASE_URL}${url}/${id}`, payload, this.header);
    }

    search(url, payload) {
        return this.http.post<any>(`${this.BASE_URL}${url}`, payload, this.header);
    }

    get(url) {
        return this.http.get<any>(`${this.BASE_URL}${url}`, this.header);
    }

    landing() {
        
        return this.http.get<any>('http://127.0.0.1:7070/',this.header)
            .pipe(map(user => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes               
                return user;
            }));
    }
}