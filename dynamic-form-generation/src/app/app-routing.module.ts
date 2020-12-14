import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
    path: 'properties',
    loadChildren: () => import('./property/property.module').then((module) => module.PropertyModule)
  },
  {
    path: '',
    redirectTo: 'properties/form',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
