import { AfterViewInit, Component, OnInit } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { FormlyFieldConfig, FormlyFormOptions } from "@ngx-formly/core";
import { forkJoin } from "rxjs";
import { PersonService } from "src/app/services/person.service";
import { PropertyService } from "src/app/services/property.service";
import { PersonValue } from "../../models/person-value.model";

@Component({
  selector: "app-person-form",
  templateUrl: "./person-form.component.html",
  styleUrls: ["./person-form.component.css"],
})
export class PersonFormComponent implements OnInit, AfterViewInit {
  form = new FormGroup({});
  model = {};
  fields: FormlyFieldConfig[] = [];
  personId: number = 0;
  options: FormlyFormOptions = {};

  constructor(
    private propertyService: PropertyService,
    private personService: PersonService,
    private activedRoute: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngAfterViewInit(): void {
  }

  ngOnInit(): void {

    this.activedRoute.params.subscribe((params: Params) => {
      if (params && params["id"]) {
        this.personId = params["id"];
      }
      this.loadProperties();
    });


  }

  loadProperties() {
    let properties = this.propertyService.getAllOrder();
    let values = this.personService.getAllByPerson(this.personId);

    forkJoin([properties, values]).subscribe((result) => {
      let data = result[0];
      let values = result[1];

      values.forEach((value) => {
        this.model[value.propertyId] = value.value;
      });

      let fieldsPrueba = [];
      data.forEach((property) => {
        if (!values) {
          this.model[property.id.toString()] = "";
        }
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
          className:
            property.typeProperty == "radio" ? "d-inline-flex flex-column" : "",
          templateOptions: {
            label: property.name,
            placeholder:
              property.typeProperty == "input" ? property.placeholder : "",
            options: property.typeProperty == "select" || "radio" ? items : [],
          },
        };
        fieldsPrueba.push(field);
      });
      this.fields = fieldsPrueba;
    });
  }

  onSubmit(model: any) {
    const personValues: PersonValue[] = [];
    for (const property of Object.keys(model)) {
      personValues.push({
        personId: 0,
        propertyId: +property,
        value: model[property],
      });
    }

    this.personService.create(personValues).subscribe(() => {
      this.snackBar.open("Persona guardada satisfactoriamente.", null, {
        duration: 2000,
      });
      this.router.navigate(["/", "persons"]);
    });
  }
}
