import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './util/auth.guard';


const routes: Routes = [
  {
    path: 'properties',
    loadChildren: () => import('./property/property.module').then((module) => module.PropertyModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'persons',
    loadChildren: () => import('./person/person.module').then((module) => module.PersonModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((module) => module.AuthModule)
  },
  {
    path: '',
    component: HomeComponent
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
