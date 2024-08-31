import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const newsHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router)
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      // if (error.status === 403) {
      //   localStorage.clear();
      //   router.navigate(['/login']); // Redirect to login page
      // }
      return throwError(()=>error);
    })
  );
};
