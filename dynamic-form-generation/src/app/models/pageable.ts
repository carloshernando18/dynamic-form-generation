export class Pageable {
  totalRows: number;
  items: Object[];

  constructor() {
    this.items = [];
    this.totalRows = 0;
  }
}
