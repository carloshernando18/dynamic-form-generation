import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = `${environment.authUrl}`;
  private TOKEN = 'token';
  private isAuthenticated = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
    const token = localStorage.getItem(this.TOKEN);
    if (token) {
      this.isAuthenticated.next(true);
    }
  }

  login(username: string, password: string) {
    return this.http.post(`${this.authUrl}/signin`, { username, password });
  }

  checkAuthenticated() {
    return this.isAuthenticated;
  }

  saveToken(token: string) {
    localStorage.setItem(this.TOKEN, token);
    this.isAuthenticated.next(true);
  }

  logout() {
    localStorage.clear();
    this.isAuthenticated.next(false);
  }

  signup(username: string, password: string) {
    return this.http.post(`${this.authUrl}/signup`, { username, password });
  }
}
