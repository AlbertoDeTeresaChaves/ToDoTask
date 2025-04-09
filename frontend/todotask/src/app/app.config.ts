import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { MatDialogModule } from '@angular/material/dialog';
import { jwtInterceptor } from './core/interceptors/jwt.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), 
              provideRouter(routes), 
              provideHttpClient(withInterceptors([jwtInterceptor])),
              importProvidersFrom(MatDialogModule)]
};
