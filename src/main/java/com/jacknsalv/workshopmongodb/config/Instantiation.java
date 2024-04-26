package com.jacknsalv.workshopmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jacknsalv.workshopmongodb.domain.CommentDTO;
import com.jacknsalv.workshopmongodb.domain.Post;
import com.jacknsalv.workshopmongodb.domain.User;
import com.jacknsalv.workshopmongodb.dto.AuthorDTO;
import com.jacknsalv.workshopmongodb.repositories.PostRepository;
import com.jacknsalv.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, Instant.parse("2018-03-21T09:28:27.141Z"), "Partiu viagem",
				"Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.parse("2018-03-21T10:30:08.000Z"), "Bom dia", 
				"Acordei feliz hoje!", new AuthorDTO(maria));

		postRepository.deleteAll();
		
		
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", Instant.parse("2018-03-21T09:28:27.141Z"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite", Instant.parse("2018-03-21T09:28:27.141Z"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2018-03-21T09:28:27.141Z"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().add(comment3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maria);
		

	}

}
