export function decodeToken(): any | null {
    const token = localStorage.getItem('jwtToken');
    if (!token) return null;
  
    try {
      const payload = token.split('.')[1];
      const decoded = atob(payload);
      return JSON.parse(decoded);
    } catch (err) {
      console.error('Erro ao decodificar o token JWT', err);
      return null;
    }
  }
  