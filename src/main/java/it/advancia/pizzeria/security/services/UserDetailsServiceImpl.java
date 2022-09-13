package it.advancia.pizzeria.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.advancia.pizzeria.model.Utente;
import it.advancia.pizzeria.repository.UtenteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UtenteRepository utenteRepository;
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Utente utente = utenteRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    return UserDetailsImpl.build(utente);
  }
}