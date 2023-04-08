package com.starpony.imageuploader;

import com.starpony.imageuploader.images.repositories.ImageRepository;
import com.starpony.imageuploader.images.repositories.LocalImageRepository;
import com.starpony.imageuploader.images.repositories.MinioImageRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@SpringBootApplication
public class ImageUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageUploaderApplication.class, args);
	}

	@Bean
	public ImageRepository imageRepository(Configuration configuration) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		Map<String, Class<?>> storageRepositories = Map.of(
				"minio", MinioImageRepository.class,
				"local", LocalImageRepository.class
		);

		String storageType = configuration.getStorageType();

		return (ImageRepository) storageRepositories.get(storageType).getConstructor(Map.class)
				.newInstance(configuration.getStorageConfiguration(storageType));
	}
}
