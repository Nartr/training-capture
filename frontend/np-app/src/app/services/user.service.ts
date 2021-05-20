import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MessageDto } from '../../dtos/MessageDto';
import { PrincipalDto } from '../../dtos/PrincipalDto';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {}

  public getTestMessage(): Observable<MessageDto> {
    return this.httpClient.get<MessageDto>('/api/test');
  }

  public getPrincipal(): Observable<PrincipalDto> {
    return this.httpClient.get<PrincipalDto>('/principal');
  }
}
