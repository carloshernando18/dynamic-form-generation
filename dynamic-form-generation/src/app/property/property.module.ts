import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { FormlyModule } from "@ngx-formly/core";
import { FormlyMaterialModule } from "@ngx-formly/material";
import { MaterialModule } from "../material.module";
import { PropertyFormComponent } from "./property-form/property-form.component";
import { PropertyListComponent } from "./property-list/property-list.component";
import { PropertyRoutingModule } from "./property-routing.module";

@NgModule({
  declarations: [PropertyListComponent, PropertyFormComponent],
  imports: [
    CommonModule,
    PropertyRoutingModule,
    ReactiveFormsModule,
    FormlyModule.forChild(),
    FormlyMaterialModule,
    MaterialModule,
  ],
})
export class PropertyModule {}
