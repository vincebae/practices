pub fn tuple_test() {
    let person_data = ("Alex", 48, "35kg", "6ft");
    let (w, x, y, z) = person_data;
    println!("Name: {}", w);
    println!("Age: {}", x);
    println!("Weight: {}", y);
    println!("Height: {}", z);
}
