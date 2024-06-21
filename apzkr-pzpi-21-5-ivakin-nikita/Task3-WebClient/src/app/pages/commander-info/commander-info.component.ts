import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Commander } from '../../services/models/commander';

@Component({
  selector: 'app-commander-info',
  templateUrl: './commander-info.component.html',
  styleUrl: './commander-info.component.scss'
})
export class CommanderInfoComponent implements OnInit {

  commander: Commander | undefined;
  type: string = "";
  
  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.commander = state.commander;
      this.type = state.type;
      console.log(this.commander);
      if (!this.commander) {
        console.error('No commander data available');
      }
    });
  }

  goBack() {
    const type = this.type;
    this.router.navigate(['/battle-groups'], { state: { type } });  
  }
}
