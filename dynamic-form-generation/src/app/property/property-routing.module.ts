import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PropertyFormComponent } from './property-form/property-form.component';
import { PropertyListComponent } from './property-list/property-list.component';

const routes: Routes = [
  {
    path: '',
    component: PropertyListComponent
  },
  {
    path: 'form',
    component: PropertyFormComponent
  },
  {
    path: 'form/:id',
    component: PropertyFormComponent
  }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule],
})
export class PropertyRoutingModule { }
