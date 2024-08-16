import { Routes } from '@angular/router';
import { redirectToLoginGuard } from './Guards/redirect-to-login.guard';
import { redirectToHomeGuard } from './Guards/redirect-to-home.guard';

export const routes: Routes = [
    {
        path:'',redirectTo:'app',pathMatch:'full'
    },
    {
        path:'app',loadComponent:()=>import("./Pages/home/home.component"),canActivate:[redirectToLoginGuard]
    },
    {
        path:'login',loadComponent:()=>import("./Pages/login/login.component"),canActivate:[redirectToHomeGuard]
    },
    {
        path:'signup',loadComponent:()=>import("./Pages/sign-up/sign-up.component"),canActivate:[redirectToHomeGuard]
    },
];
