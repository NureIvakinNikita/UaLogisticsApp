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

  
  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.commander = state.commander;
      console.log(this.commander);
      if (!this.commander) {
        console.error('No commander data available');
      }
    });
  }

  goBack() {
    this.router.navigate(['/battle-groups']);
  }
}
