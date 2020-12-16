import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PersonValue } from '../models/person-value.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private propertyUrl = `${environment.apiUrl}persons`;

  constructor(private http: HttpClient) { }

  create(values: PersonValue[]) {
    return this.http.post<PersonValue[]>(this.propertyUrl, values);
  }

  getAllByPerson(personId: number): Observable<PersonValue[]> {
    return this.http.get<PersonValue[]>(`${this.propertyUrl}/${personId}`);
  }
}
