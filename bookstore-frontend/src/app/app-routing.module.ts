import {Routes, RouterModule} from "@angular/router";
import {BookListComponent} from "./components/book-list/book-list.component";
import {NgModule} from "@angular/core";
import {BookDetailComponent} from "./components/book-detail/book-detail.component";

const routes: Routes = [
	{path: '', redirectTo: '/book-list', pathMatch: 'full'},
	{path: 'book-list', component: BookListComponent},
	{path: 'book-detail/:id', component: BookDetailComponent},
	{path: 'new-book', component: BookDetailComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})

export class AppRoutingModule {
}