import { AfterViewInit, Component, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { merge, of } from "rxjs";
import { catchError, map, startWith, switchMap } from "rxjs/operators";
import { ColumnDescription } from "src/app/models/column-description.model";
import { Property } from "src/app/models/property.model";
import { PropertyService } from "src/app/services/property.service";

@Component({
  selector: "app-property-list",
  templateUrl: "./property-list.component.html",
  styleUrls: ["./property-list.component.css"],
})
export class PropertyListComponent implements AfterViewInit {
  displayedColumns: ColumnDescription[] = [
    { attributeName: "name", columnTitle: "Nombre" },
    { attributeName: "typeProperty", columnTitle: "Tipo" },
    { attributeName: "orderProperty", columnTitle: "Orden" },
    { attributeName: "required", columnTitle: "Es Requerido", columnType: 'boolean' },
    { attributeName: "edit", columnTitle: "Editar", columnType: 'action' },
  ];
  datasource: MatTableDataSource<Property>;
  displayedRows: string[] = this.displayedColumns.map(function (item) {
    return item["attributeName"];
  });
  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private propertyService: PropertyService) {}

  ngAfterViewInit() {
    this.loadProperties();
  }

  loadProperties(filter: string = "") {
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
          return this.propertyService.getAll(
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
    this.loadProperties(filter);
  }
}
