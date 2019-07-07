import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {SecondPageComponent} from "./second-page/second-page.component";
import {ThirdPageComponent} from "./third-page/third-page.component";
import {FirstPageComponent} from "./first-page/first-page.component";

const routes: Routes = [
    {path: "", component: FirstPageComponent},
    {path: "second/:id", component: SecondPageComponent},
    {path: "third", component: ThirdPageComponent},
    {path: "**", redirectTo: "/"}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
