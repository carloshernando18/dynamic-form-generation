import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pageable } from '../models/pageable';
import { PersonValue } from '../models/person-value.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private personUrl = `${environment.apiUrl}persons`;

  constructor(private http: HttpClient) { }

  getAll(sort: string, order: string, page: number, size: number, filter: string): Observable<Pageable> {
    const requestUrl =
      `${this.personUrl}?page=${page}&size=${size}&sort=${sort},${order}&filter=${filter}`;

    return this.http.get<Pageable>(requestUrl);
  }

  create(values: PersonValue[]) {
    return this.http.post<PersonValue[]>(this.personUrl, values);
  }

  getAllByPerson(personId: number): Observable<PersonValue[]> {
    return this.http.get<PersonValue[]>(`${this.personUrl}/${personId}`);
  }
}
