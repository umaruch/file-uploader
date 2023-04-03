package com.starpony.imageuploader;

import com.starpony.imageuploader.configuration.MinioConfiguration;
import com.starpony.imageuploader.repositories.ImageRepository;
import com.starpony.imageuploader.repositories.MinioImageRepository;
import io.minio.MinioClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageUploaderApplication.class, args);
	}

	@Bean
	public ImageRepository imageRepository(MinioConfiguration minioConfiguration) {
		MinioClient minioClient = MinioClient.builder().endpoint(minioConfiguration.getAddress())
				.credentials(minioConfiguration.getAccessKey(), minioConfiguration.getSecretKey()).build();

		return new MinioImageRepository(minioClient);
	}
}
