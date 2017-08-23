package felix.flappytrump.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private Rectangle bounds;
    private int gravity = -17;
    private Vector3 position;
    private int speed = 100;
    private Vector3 velocity;

    public Bird(int var1, int var2) {
        this.position = new Vector3((float)var1, (float)var2, 0.0F);
        this.velocity = new Vector3(0.0F, 0.0F, 0.0F);
        this.bounds = new Rectangle((float)var1, (float)var2, 50.0F, 50.0F);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public void jump() {
        this.velocity.y = 250.0F;
    }

    public void update(float var1) {
        if(this.position.y > 0.0F) {
            this.velocity.add(0.0F, (float)this.gravity, 0.0F);
        }

        this.velocity.scl(var1);
        this.position.add((float)this.speed * var1, this.velocity.y, 0.0F);
        if(this.position.y < 0.0F) {
            this.position.y = 0.0F;
        }

        this.velocity.scl(1.0F / var1);
        this.bounds.setPosition(this.position.x, this.position.y);
    }
}
