import { Component } from '@angular/core';
import { FieldType } from '@ngx-formly/core';

@Component({
  selector: 'formly-date-picker',
  template: `
    <mat-form-field appearance="fill" >
      <mat-label>{{ to.label }}</mat-label>
      <input
        matInput
        [matDatepicker]="picker1"
        [placeholder]="to.label"
        [formControl]="formControl"
        [class.is-invalid]="showError"
      />
      <mat-datepicker-toggle matSuffix [for]="picker1"></mat-datepicker-toggle>
      <mat-datepicker #picker1></mat-datepicker>
    </mat-form-field>
  `,
  styles: [`
    mat-form-field {
      width: 100%
    };


  `]
})
export class DatePickerComponent extends FieldType {}
