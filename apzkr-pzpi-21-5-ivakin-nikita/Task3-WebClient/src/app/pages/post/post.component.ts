import { Component, OnInit } from '@angular/core';
import { Post, PostDto, ScanningDevice } from '../../services/models';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminControllerService, AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent implements OnInit {
  errorMsg: Array<string> = [];
  post: PostDto = {id: 0, location: "", scanningDeviceId: 0};
  actionType: string = "";
  device: ScanningDevice = {id: 0, tier: 0};
  id:number = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthenticationService,
    private tokenService: TokenService,
    private adminService: AdminControllerService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(() => {
      const state = window.history.state;
      this.actionType = state.actionType;
      this.id = state.id;
    });
  }

  onSubmit() {
    if (this.actionType === 'add') {
      this.addPost();
    } else {
      this.assignDevice();
    }
  }

  addPost() {
    this.adminService.createPost({body : this.post}).subscribe({
      next: (response) => {
        console.log('Battalion created successfully');
        this.router.navigate(['/posts']);  
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || err.error.message;
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  assignDevice(){
    this.adminService.createScanningDevice({id: this.id, body: this.device}).subscribe({
      next: () => {
        console.log('Device assigned successfully');
        this.router.navigate(['/posts']);  
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [err.error.message];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  onCancel() {
    this.router.navigate(['/posts']);
  }
}
