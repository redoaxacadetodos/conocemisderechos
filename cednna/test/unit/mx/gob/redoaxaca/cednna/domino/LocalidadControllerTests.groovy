package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(LocalidadController)
@Mock(Localidad)
class LocalidadControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/localidad/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.localidadInstanceList.size() == 0
        assert model.localidadInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.localidadInstance != null
    }

    void testSave() {
        controller.save()

        assert model.localidadInstance != null
        assert view == '/localidad/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/localidad/show/1'
        assert controller.flash.message != null
        assert Localidad.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/localidad/list'

        populateValidParams(params)
        def localidad = new Localidad(params)

        assert localidad.save() != null

        params.id = localidad.id

        def model = controller.show()

        assert model.localidadInstance == localidad
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/localidad/list'

        populateValidParams(params)
        def localidad = new Localidad(params)

        assert localidad.save() != null

        params.id = localidad.id

        def model = controller.edit()

        assert model.localidadInstance == localidad
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/localidad/list'

        response.reset()

        populateValidParams(params)
        def localidad = new Localidad(params)

        assert localidad.save() != null

        // test invalid parameters in update
        params.id = localidad.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/localidad/edit"
        assert model.localidadInstance != null

        localidad.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/localidad/show/$localidad.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        localidad.clearErrors()

        populateValidParams(params)
        params.id = localidad.id
        params.version = -1
        controller.update()

        assert view == "/localidad/edit"
        assert model.localidadInstance != null
        assert model.localidadInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/localidad/list'

        response.reset()

        populateValidParams(params)
        def localidad = new Localidad(params)

        assert localidad.save() != null
        assert Localidad.count() == 1

        params.id = localidad.id

        controller.delete()

        assert Localidad.count() == 0
        assert Localidad.get(localidad.id) == null
        assert response.redirectedUrl == '/localidad/list'
    }
}
