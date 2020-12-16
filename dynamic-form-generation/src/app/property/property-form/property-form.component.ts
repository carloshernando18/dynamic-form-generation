import { Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { FormlyFieldConfig } from "@ngx-formly/core";
import { Property } from "src/app/models/property.model";
import { PropertyService } from "src/app/services/property.service";

@Component({
  selector: "app-property-form",
  templateUrl: "./property-form.component.html",
  styleUrls: ["./property-form.component.css"],
})
export class PropertyFormComponent implements OnInit {
  form = new FormGroup({});
  model = {
    id: 0,
    name: '',
    typeProperty: null,
    orderProperty: 0,
    maxLength: null,
    pattern: null,
    required: false,
    options: '',
    placeholder: '',
  };
  fields: FormlyFieldConfig[] = [
    {
      key: "id",
    },
    {
      key: "name",
      type: "input",
      templateOptions: {
        label: "Nombre",
        placeholder: "Nombre de la propiedad",
        required: true,
      },
    },
    {
      key: "orderProperty",
      type: "input",
      templateOptions: {
        type: "number",
        label: "Orden",
        placeholder: "Orden de visualización de la propiedad",
        required: true,
      },
    },
    {
      key: "typeProperty",
      type: "select",
      templateOptions: {
        label: "Tipo de la Propiedad",
        placeholder: "Tipo de la propiedad",
        required: true,
        options: [
          {
            label: "input",
            value: "input",
          },
          {
            label: "checkbox",
            value: "checkbox",
          },
          {
            label: "radio",
            value: "radio",
          },
          {
            label: "select",
            value: "select",
          },
          {
            label: "date",
            value: "date",
          },
        ],
      },
    },
    {
      key: "maxLength",
      type: "input",
      templateOptions: {
        type: "number",
        label: "Longitud máxima",
        placeholder: "Longitud máxima de la propiedad",
      },
      hideExpression: "model.typeProperty != 'input'",
    },
    {
      key: "pattern",
      type: "input",
      templateOptions: {
        label: "Patrón de validación",
        placeholder: "Patrón de validación de la propiedad",
      },
      hideExpression: "model.typeProperty != 'input'",
    },
    {
      key: "options",
      type: "input",
      templateOptions: {
        label: "opciones",
        placeholder: "Escriba los valores de la lista seprados por comas (,) Ej: ítem_1, ítem_2",
      },
      hideExpression: "model.typeProperty != 'select' && model.typeProperty != 'radio'",
    },
    {
      key: "required",
      type: "checkbox",
      templateOptions: {
        label: "Es requerido",
      },
    },
  ];

  constructor(
    private propertyService: PropertyService,
    private activedRoute: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.activedRoute.params.subscribe((params: Params) => {
      console.log(params);
      if (params && params["id"]) {
        const id = params["id"];
        this.propertyService.getById(id).subscribe((data) => {
          this.model = data;
        });
      }
    });
  }

  onSubmit(model: Property) {
    if (model.id > 0) {
      this.propertyService.update(model).subscribe(() => {
        this.snackBar.open("Propiedad editada satisfactoriamente.", null, {
          duration: 2000,
        });
        this.router.navigate(["/", "properties"]);
      });
    } else {
      this.propertyService.create(model).subscribe(() => {
        this.snackBar.open("Propiedad guardada satisfactoriamente.", null, {
          duration: 2000,
        });
        this.router.navigate(["/", "properties"]);
      });
    }
  }
}
