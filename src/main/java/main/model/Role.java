package main.model;

// Η enum Role για να διασφαλίσει μόνο συγκεκριμένες τιμές
public enum Role {
	CLIENT, // Πελάτης
	SELLER, // Πωλητής
	ADMIN; // Διαχειριστής

	// Μετατροπή της τιμής της enum σε πεζά γράμματα
	public String toLowercase() {
		return this.name().toLowerCase();
	}
}
