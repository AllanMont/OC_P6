import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PostComponent } from './pages/post/post.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TopicComponent } from './pages/topic/topic.component';
import { PostDetailComponent } from './pages/post-detail/post-detail.component';
import { PostCreateComponent } from './pages/post-create/post-create.component';
import { AuthGuard } from './core/guards/auth.guard';
import { UnauthGuard } from './core/guards/unAuth.guard';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent, canActivate: [UnauthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [UnauthGuard] },
  { path: 'post', component: PostComponent, canActivate: [AuthGuard] },
  { path: 'post-detail/:id', component: PostDetailComponent, canActivate: [AuthGuard] },
  { path: 'post-create', component: PostCreateComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'topic', component: TopicComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
