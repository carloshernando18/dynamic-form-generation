import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
    path: 'properties',
    loadChildren: () => import('./property/property.module').then((module) => module.PropertyModule)
  },
  {
    path: 'persons',
    loadChildren: () => import('./person/person.module').then((module) => module.PersonModule)
  },
  {
    path: '',
    redirectTo: 'persons/form',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
