fn move_ownership(c: Box<i32>) {
    println!("Destroying a box that contains {}", c);
}

pub fn ownership_test() {
    let a = Box::new(5_i32);
    println!("a contains: {}", a);

    let b = a;
    // println!("a contains: {}", a);
    println!("b contains: {}", b);

    let c = &b;
    println!("c contains: {}", c);

    move_ownership(b);
    // println!("b contains: {}", b);

    let mut x = 10_000_i32;
    // println!("x: {}", x);
    // x = 15;

    let y = &x;
    println!("x: {}", x);
    println!("y: {}", y);

    let z = &mut x;
    println!("z: {}", z);
    *z = 20;
    // x = 18;

    println!("z: {}", z);
    println!("x: {}", x);

    x = 50;
    println!("x: {}", x);

    let w = &mut x;
    *w = 100;

    println!("w: {}", w);
    println!("x: {}", x);

    let f: f64 = 3.1;
    println!("f: {}", f);
}
