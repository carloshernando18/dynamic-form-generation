import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { FormlyFieldConfig } from "@ngx-formly/core";
import { PropertyService } from "src/app/services/property.service";

@Component({
  selector: "app-person-form",
  templateUrl: "./person-form.component.html",
  styleUrls: ["./person-form.component.css"],
})
export class PersonFormComponent implements OnInit {
  form = new FormGroup({});
  model = {};
  fields: FormlyFieldConfig[] = [];

  constructor(private propertyService: PropertyService) {}

  ngOnInit(): void {
    this.loadProperties();
  }

  loadProperties() {
    this.propertyService.getAllOrder().subscribe((data) => {
      let fieldsPrueba = [];
      data.forEach((property) => {
        console.log(property);
        let options = property.options.split(",");
        let items = [];
        options.forEach((option) => {
          let item = {
            label: option,
            value: option,
          };
          items.push(item);
        });

        const field: FormlyFieldConfig = {
          key: property.id.toString(),
          type: property.typeProperty,
          templateOptions: {
            label: property.name,
            placeholder:
              property.typeProperty == "input" ? property.placeholder : "",
            options: property.typeProperty == "select" ? items : [],
          },
        };
        fieldsPrueba.push(field);
      });
      this.fields = fieldsPrueba;
    });
  }
}
