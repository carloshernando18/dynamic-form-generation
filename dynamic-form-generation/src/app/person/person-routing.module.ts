import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonFormComponent } from './person-form/person-form.component';

const routes: Routes = [
  {
    path: 'form',
    component: PersonFormComponent
  },
  {
    path: 'form/:id',
    component: PersonFormComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonRoutingModule { }
