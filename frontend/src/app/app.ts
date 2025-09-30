import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './pages/header/header';
//import { Header } from "./component/header/header";

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet, Header],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
 title = signal('prasad');

}
