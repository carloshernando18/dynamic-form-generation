import { AfterViewInit, Component, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { merge, of } from "rxjs";
import { catchError, map, startWith, switchMap } from "rxjs/operators";
import { ColumnDescription } from "src/app/models/column-description.model";
import { Person } from 'src/app/models/person.model';
import { PersonService } from 'src/app/services/person.service';


@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements AfterViewInit {
  displayedColumns: ColumnDescription[] = [
    { attributeName: "name", columnTitle: "Nombre" },
    { attributeName: "date", columnTitle: "Fecha" },
    { attributeName: "edit", columnTitle: "Editar", columnType: 'action' },
  ];

  datasource: MatTableDataSource<Person>;
  displayedRows: string[] = this.displayedColumns.map(function (item) {
    return item["attributeName"];
  });
  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private personService: PersonService) { }

  ngAfterViewInit() {
    this.loadPersons();
  }

  loadPersons(filter: string = "") {
    if (filter.length > 3) {
      this.paginator.pageIndex = 0;
      this.paginator.pageSize = 10;
    }

    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.personService.getAll(
            this.sort.active,
            this.sort.direction,
            this.paginator.pageIndex,
            this.paginator.pageSize,
            filter
          );
        }),
        map((data) => {
          this.isLoadingResults = false;
          this.resultsLength = data.totalRows;

          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return of([]);
        })
      )
      .subscribe((data) => {
        this.datasource = new MatTableDataSource(data);
        this.datasource.sort = this.sort;
      });
  }

  applyFilter(filter: string) {
    this.loadPersons(filter);
  }
}
