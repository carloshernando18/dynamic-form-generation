import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pageable } from '../models/pageable';
import { Property } from '../models/property.model';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  private propertyUrl = `${environment.apiUrl}properties`;

  constructor(private http: HttpClient) { }

  getAll(sort: string, order: string, page: number, size: number, filter: string): Observable<Pageable> {
    const requestUrl =
      `${this.propertyUrl}?page=${page}&size=${size}&sort=${sort},${order}&filter=${filter}`;

    return this.http.get<Pageable>(requestUrl);
  }

  getById(id: number) {
    return this.http.get<Property>(`${this.propertyUrl}/${id}`);
  }

  create(property: Property) {
    return this.http.post(this.propertyUrl, property);
  }

  update(property: Property) {
    return this.http.put(`${this.propertyUrl}/${property.id}`, property);
  }
}
