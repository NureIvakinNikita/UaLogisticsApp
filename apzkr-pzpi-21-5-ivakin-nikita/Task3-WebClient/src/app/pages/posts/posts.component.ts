import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService, AdminControllerService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';
import { PostDto } from '../../services/models';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrl: './posts.component.scss'
})
export class PostsComponent implements OnInit {

  posts: PostDto[] = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService,
    private adminService: AdminControllerService
  ) {}

  ngOnInit() {
    if (this.tokenService.getRoleFromToken() === 'ADMIN') {
      this.loadPosts();
    }
  }


  loadPosts() {
    this.adminService.getPosts().subscribe(
      (data: PostDto[]) => {
        this.posts = data;
      },
      (error) => {
        console.error('Error loading posts', error);
      }
    );
  }

  addPost() {
    const actionType = "add";
    this.router.navigate(['/create/post'], {state: { actionType }});
  }

  assignDevice(id: number) {
    const actionType = "assign";
    this.router.navigate(['/assign/device'], {state: { actionType, id }});
  }

}
