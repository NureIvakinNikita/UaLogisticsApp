import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  set token(token:string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token') as string;
  }

  getRoleFromToken(): string {
    const token = this.token; 
    if (token?.length > 0) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.role;
    }
    throw new Error('User isn\'t authenticated');
  }
}
